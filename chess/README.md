### Requirements for the chess game:

- **R1:**  The system must enable two players (human or machine) to play chess online, supporting real-time, turn-based multiplayer gameplay.
- **R2:**  The chess game must strictly enforce the official rules of international (standard) chess.
- **R3:**  At the beginning of each game, players will be randomly assigned white or black.
- **R4:**  Each player starts with 16 pieces: eight pawns, two rooks, two knights, two bishops, one queen, and one king, placed in the official starting positions on the 8x8 chessboard.
- **R5:**  The player with the white pieces always makes the first move.
- **R6:**  Players may not retract or undo their moves once made. (No “take-back” or undo functionality.)
- **R8:**  The game must support all official ways a chess game can end, including:
  - **Checkmate** (a player’s king is under attack and cannot escape)
  - **Stalemate** (no legal moves and the king is not in check)
  - **Draw** (mutual agreement, threefold repetition, 50-move rule, or insufficient material)
  - **Resignation** (a player concedes defeat)
  - **Forfeiture** (a player does not show up or abandons the game)
- **R9:**  According to international chess rules, the system must support special moves such as castling, en passant, and pawn promotion.