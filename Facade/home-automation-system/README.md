# LLD Question: Design a Home Automation System
You are asked to design a Home Automation System where a user can control multiple smart devices from a central controller.

## The system should support:
- Smart Lights (can turn on/off, dim, change color)
- Smart Thermostat (set temperature)
- Smart Door Lock (lock/unlock)
- Smart Curtains (open/close)
- Security Camera (start/stop recording)

## Requirements:
The user should be able to control individual devices.
There should also be a **"Leaving Home"** and **"Coming Home"** feature that executes a sequence of operations across multiple devices with a single command (e.g., turning off lights, locking doors, etc.).
Your design should hide the complexity from the user when executing these scenarios.

### Bonus: How would you design it so that adding new types of devices requires minimal code changes?
### Hints (what interviewers look for):
✅ How you break down subsystems (each device)  
✅ How you apply Facade Pattern to simplify user interaction  
✅ How extensibility is handled (maybe with interfaces/abstraction)