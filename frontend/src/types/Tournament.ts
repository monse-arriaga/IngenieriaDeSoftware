interface Tournament {
    name: string,
    noPlayers: number, 
    about: string,
    status: string,
    tournamentType: string,
    playerType: string,
    inPlayers: number,
    date: Date,
    prize: number
}

export default Tournament;