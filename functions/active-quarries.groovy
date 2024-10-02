def player = context.valueFor('name-to-id', 'id')
int quarries = 0
for (i in 1..8) {
    if (context.valueFor('quarry'+i, 'owner') == player
            && context.valueFor('quarry'+i, 'workers') == '1') {
        quarries++
    }
}
quarries