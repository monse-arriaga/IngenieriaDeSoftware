interface Tournament {
    name: string,
    players: number, 
    description: string,
    state: string,
    tournamentType: string,
    tournamentGame : string,
    playersBT: number,
    inPlayers: number,
    date: string,
    prize: number,
    time: string
}

export default Tournament;