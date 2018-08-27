    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
    <%  
    String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
    %>  
      
    <?xml version="1.0" encoding="UTF-8"?>  
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">  
    <head>  
        <title>测试</title>  
        <style type="text/css">  
            input#chat {  
                width: 410px  
            }  
      
            #console-container {  
                width: 400px;  
            }  
      
            #console {  
                border: 1px solid #CCCCCC;  
                border-right-color: #999999;  
                border-bottom-color: #999999;  
                height: 170px;  
                overflow-y: scroll;  
                padding: 5px;  
                width: 100%;  
            }  
      
            #console p {  
                padding: 0;  
                margin: 0;  
            }  
     </style>  
        <script type="text/javascript">  
      
            var Chat = {};  
      
            Chat.socket = null;  
      
            Chat.connect = (function(host) {  
                if ('WebSocket' in window) {  
                    Chat.socket = new WebSocket(host);  
                } else if ('MozWebSocket' in window) {  
                    Chat.socket = new MozWebSocket(host);  
                } else {  
                    Console.log('Error: WebSocket is not supported by this browser.');  
                    return;  
                }  
      
                Chat.socket.onopen = function () {  
                    Console.log('Info: WebSocket connection opened.');  
                    document.getElementById('chat').onkeydown = function(event) {  
                        if (event.keyCode == 13) {  
                            Chat.sendMessage();  
                        }  
                    };  
                };  
      
                Chat.socket.onclose = function () {  
                    document.getElementById('chat').onkeydown = null;  
                    Console.log('Info: WebSocket closed.');  
                };  
      
                Chat.socket.onmessage = function (message) {  
                    Console.log(message.data);  
                };  
            });  
      
            Chat.initialize = function() {  
                if (window.location.protocol == 'http:') {  
                    Chat.connect('ws://127.0.0.1:9080/im_platform/chat');  
                } else {  
                    Chat.connect('wss://127.0.0.1:9080/im_platform/chat');  
                }  
            };  
      
            Chat.sendMessage = (function() {  
                var message = document.getElementById('chat').value;  
                if (message != '') {  
                    Chat.socket.send(message);  
                    document.getElementById('chat').value = '';  
                }  
            });  
      
            var Console = {};  
      
            Console.log = (function(message) {  
                var console = document.getElementById('console');  
                var p = document.createElement('p');  
                p.style.wordWrap = 'break-word';  
                p.innerHTML = message;  
                console.appendChild(p);  
                while (console.childNodes.length > 25) {   
                    console.removeChild(console.firstChild);  
                }  
                console.scrollTop = console.scrollHeight;  
            });  
      
            Chat.initialize();  
      
      
            document.addEventListener("DOMContentLoaded", function() {  
                // Remove elements with "noscript" class - <noscript> is not allowed in XHTML  
                var noscripts = document.getElementsByClassName("noscript");  
                for (var i = 0; i < noscripts.length; i++) {  
                    noscripts[i].parentNode.removeChild(noscripts[i]);  
                }  
            }, false);  
      
       </script>  
    </head>  
    <body>  
    <div class="noscript"><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable  
        Javascript and reload this page!</h2></div>  
    <div>  
        <p>  
            <input type="text" placeholder="请输入内容" id="chat" />  
        </p>  
        <div id="console-container">  
            <div id="console"/>  
        </div>  
    </div>  
    </body>  
    </html>  