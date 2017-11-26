'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// Cut off time. Child nodes older than this will be deleted.

/**
 * This database triggered function will check for child nodes that are older than the
 * cut-off time. Each child needs to have a `timestamp` attribute.
 */
exports.deleteExpiredOffers = functions.database.ref('/offers/{pushId}')
    .onWrite(event => {
        const ref = event.data.ref.parent; // reference to the items
        const now = Date.now();
        const oldItemsQuery = ref.orderByChild('expiryDate').endAt(now);
        return oldItemsQuery.once('value').then(snapshot => {
            // create a map with all children that need to be removed
            const updates = {};
            snapshot.forEach(child => {
                updates[child.key] = null;
            });
// execute all updates in one go and return the result to end the function
            return ref.update(updates);
        });
    });


function sendNotification(user, payload) {
    const userId = user.providerData.uid;
    admin.database().ref(`/users/${userId}/notificationTokens`).once('value')
        .then((tokensSnapshot)=>{
            if (!tokensSnapshot.hasChildren()) {
                console.log('There are no notification tokens to send to.');
                return;
            }
            const tokens = Object.keys(tokensSnapshot.val());

            return admin.messaging().sendToDevice(tokens, payload).then(response => {
                // For each message check if there was an error.
                const tokensToRemove = [];
                response.results.forEach((result, index) => {
                    const error = result.error;
                    if (error) {
                        console.error('Failure sending notification to', tokens[index], error);
                        // Cleanup the tokens who are not registered anymore.
                        if (error.code === 'messaging/invalid-registration-token' ||
                            error.code === 'messaging/registration-token-not-registered') {
                            tokensToRemove.push(tokensSnapshot.ref.child(tokens[index]).remove());
                        }
                    }
                });
                return Promise.all(tokensToRemove);
            });

        })

}

exports.sendMessageNotifications = functions.database.ref('/messages/{pushId}')
    .onWrite(event => {
        const messageData = event.data; // messageData

        console.log("Message Data:", messageData);
        const userName = messageData.name;
        return admin.auth().listUsers(1000)
            .then(function (listUsersResult) {

                const sendingUser = listUsersResult.users.find((userRecord)=>{
                    return userRecord.displayName === userName;
                });

                const payload = {
                    notification: {
                        title: 'You got a message!',
                        body: `${sendingUser.displayName} replied to your offer.`,
                        icon: sendingUser.photoURL
                    }
                };

                const recevingUsers = listUsersResult.users.filter((userRecord)=>{
                    return userRecord.displayName !== userName;
                });

                recevingUsers.forEach((user)=>{
                    sendNotification(user,payload)
                })
            });
    });
