### Requirements for online stock order system

- [x] **R1:** The system should allow the user to easily trade in stocks (buy or sell the stocks).
- [x] **R2:** Users can have numerous watchlists consisting of different stock quotes.
- [x] **R3:** Users may own different lots of the same stock. If a user has purchased the same stock more than once, the system should be able to distinguish between several lots of it.
- [x] **R4:** The system should be able to notify users whenever a trade order is executed.
- [x] **R5:** The system should allow the user to order the stock trade of the types given below:
  - [x] **Market order:** Buy or sell stocks at the current market price.
  - [ ] **Limit order:** Buy or sell stocks at the price set by the user.
  - [x] **Stop-loss order:** Buy or sell stocks when they reach a certain price.
  - [ ] **Stop-limit order:** Buy or sell stocks with a restriction on the limit price (maximum price to be paid, minimum price to be received, etc).

#### Note:
Currently, I have created a 0-1 fill. We can extend to partial fills easily.