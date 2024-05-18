import MatchStatus from "./MatchStatus";

interface MatchGame {
    id: number,
    matchStatus: MatchStatus,
    opponentOneResult: {id: number},
    opponentTwoResult: {id: number},
    stage: {id: number},
    match: {id: number},
    number: number
}

export default MatchGame