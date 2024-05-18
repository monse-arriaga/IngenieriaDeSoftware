import MatchGame from "./MatchGame";
import MatchStatus from "./MatchStatus";

interface Match {
    id: number,
    matchStatus: MatchStatus,
    opponentOneResult: {id: number},
    opponentTwoResult: {id: number},
    stage: {id: number},
    group: {id: number},
    round: {id: number},
    number: number,
    childCount: number,
    games: Array<MatchGame>
}

export default Match