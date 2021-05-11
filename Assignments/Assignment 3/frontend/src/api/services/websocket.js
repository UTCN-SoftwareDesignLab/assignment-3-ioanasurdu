import SockJS from "sockjs-client"
import Stomp from "stomp-client"

export default {
    websocket(message) {
        this.stompClient.send("/app/consultation", {}, JSON.stringify(message));
    },
    connected() {
        var socket = new SockJS('/gs-guide-websocket');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/message/consultations', function (message) {
                // eslint-disable-next-line no-undef
                showMessage(JSON.parse(message.body).message);
            });
        });
    },
    disconnect() {
        var stompClient = null;
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    },
    showMessage(message) {
        // eslint-disable-next-line no-undef
        $("#consultations").append("<tr><td>" + message + "</td></tr>");
    }
};
