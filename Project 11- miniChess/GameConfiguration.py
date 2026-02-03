def game_configuration():
    alpha_beta_active = None
    timeout_time = None
    max_number_turns = None
    player_1_type = None
    player_2_type = None
    player_1_heuristic = None
    player_2_heuristic = None

    while True:
        alpha_beta_active = input("Alpha-beta active? Enter 'True' or 'False': ").strip().lower()  # If alpha-beta is active for the AI, if no AI, remains False
        if alpha_beta_active == "true":
            alpha_beta_active = True
            break
        elif alpha_beta_active == "false":
            alpha_beta_active = False
            break
        print("Invalid input.")
    while True:
        try:
            timeout_time = int(input("Timeout time for AI in seconds? Enter an integer greater than zero: ").strip())  # Value of the timeout in second for AI
            if timeout_time > 0:
                break
            else:
                print("Invalid input.")
        except ValueError:
            print("Invalid input.")  # Handle non-integer inputs
    while True:
        try:
            max_number_turns = int(input("Max number of turns? Enter an integer greater than zero: ").strip())  # Max number of turns
            if max_number_turns > 0:
                break
            else:
                print("Invalid input.")
        except ValueError:
            print("Invalid input.")  # Handle non-integer inputs
    while True:
        player_1_type = input("Player ONE type? Enter 'AI' or 'Human': ").strip().upper()
        if player_1_type == "AI" or player_1_type == "HUMAN":
            break
        print("Invalid input.")
    if player_1_type == "HUMAN":
        player_1_type = "H"
    else:
        while True:
            player_1_heuristic = input("Player ONE heuristic? Enter 'e0', 'e1', 'e2': ")
            if player_1_heuristic == "e0" or player_1_heuristic == "e1" or player_1_heuristic == "e2":
                break
            print("Invalid input.")
    while True:
        player_2_type = input("Player TWO type? Enter 'AI' or 'Human': ").strip().upper()
        if player_2_type == "AI" or player_2_type == "HUMAN":
            break
        print("Invalid input.")
    if player_2_type == "HUMAN":
        player_2_type = "H"
    else:
        while True:
            player_2_heuristic = input("Player TWO heuristic? Enter 'e0', 'e1', 'e2': ")
            if player_2_heuristic == "e0" or player_2_heuristic == "e1" or player_2_heuristic == "e2":
                break
            print("Invalid input.")

    return alpha_beta_active, str(timeout_time), str(max_number_turns), str(player_1_type), str(player_2_type), player_1_heuristic, player_2_heuristic