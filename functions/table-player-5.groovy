int numPlayers = context.valueFor('table', 'playersNum').toInteger()

List<String> players = new ArrayList<String>()
for (int i = 1; i <= numPlayers; i++) {
    players.add(context.valueFor('table', 'player' + i))
}

players.remove(context.valueFor('player1', 'name'))
players.remove(context.valueFor('player2', 'name'))
players.remove(context.valueFor('player3', 'name'))
players.remove(context.valueFor('player4', 'name'))

players[Math.ceil(Math.random() * (players.size() - 1)).toInteger()]
