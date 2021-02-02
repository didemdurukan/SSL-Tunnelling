# SSL-Tunnelling
A socket tunneling system that supports TCP connections

Our socket tunnelling system that will
support both UDP and TCP connections. This system is useful for (a)
encrypting non-secure protocols such as POP3, HTTP and IMAP and (b) bypassing
deep packet inspection enabled firewall and routers. In order to achieve this, the
proposed project requires the utilization of client-server model, where the node
behind the firewall acts as a client and the node on the open Internet acts as a
server. 

This system creates an encrypted socket tunnel between the sender and the reciever device.

![SOCKET](https://user-images.githubusercontent.com/40358283/106638255-f75b2980-6583-11eb-8e07-36f826166155.JPG)

## Client Side

* On the client side, there is a process that listens to one or more TCP
ports based on the settings given by the user. The incoming connection can be
encrypted or plain data. 

* In order for you to achieve this, I created my own private key and
certificates pair using OpenSSL, which is freely available for Linux, Windows and
Macs. Furthermore, I created a PEM (Privacy-Enhanced Mail) file
combining the newly generated private key and certificate.

* Eventually,  client application should then be able to encrypt any incoming data
stream and relay it to the socket tunnel server’s listening port.

## Server Side

* On the server side, the socket tunnel server is listening to a specific port
and relaying any incoming data to a predefined local port or to any third party
service.

![fig2ssl](https://user-images.githubusercontent.com/40358283/106638351-13f76180-6584-11eb-842b-2328ab216e08.JPG)

## Application Logic

* Both peers – socket tunnel client and server – are actually acting as both client and
server. The client peer is required to accept incoming local communication (acting
as server) and relaying that data by encrypting it to the socket tunnel server. On
the other hand, the socket tunnel server is required to accept incoming
communication from the client, but also relay that data to local or remote service
(acting as a client). To achieve this, both clients and server has a configuration file.

* All communication between the tunnel nodes – client and server - are
encrypted using SSL/TLS. 

## Development Environment

* Coding language is Java and Eclipse is used as an IDE.
