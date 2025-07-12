# Design a Chat Room System

You're tasked with designing a chat room system for a team collaboration app (similar to Slack or Microsoft Teams).  
The system should allow multiple users to join a chat room and send messages to each other.  
However, instead of users communicating with each other directly, all communication should go through a central ChatRoom mediator.

## Requirements:

- Users can send messages to the chat room.
- The chat room is responsible for delivering the message to all users except the sender.
- Users should not directly communicate with each other.
- The system should be extendable to support private messaging in the future.
### (Optional for follow-up) Support multiple chat rooms, where users can be part of different rooms.
#### 💡 Hints:
- Think about what class should act as the Mediator.
- Ensure the colleagues (users) are decoupled from each other and rely only on the mediator for communication.
- Keep the code open for extensibility (e.g., different message types or filtering).