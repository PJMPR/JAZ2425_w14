const stompClient = new StompJs.Client({
    brokerURL: '/ws'
})
stompClient.activate();

// stompClient.onConnect = (frame) => {
//     console.log('Connected: ' + frame);
//     stompClient.subscribe('/queue/messages', (message) => {
//         handleIncomingMessage(message);
//     });
// };
//
// function disconnect() {
//     stompClient.deactivate();
//     console.log("Disconnected");
// }
//
//
//
// function sendMessage() {
//     stompClient.publish({
//         destination: "/app/chat",
//         body: JSON.stringify({'content': getMessage()})
//     });
// }



function handleIncomingMessage(message) {
    console.log(message.body)
    const messageArea = document.getElementById('messageArea');
    const newMessage = document.createElement('div');
    const jsonMessage = JSON.parse(message.body);
    newMessage.textContent = jsonMessage.content;
    messageArea.appendChild(newMessage);
}

function getMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value;
    return message;
}