import MatchStatus from "./MatchStatus";
import ParticipantMatchGameResult from "./ParticipantMatchGameResult";

interface MyMatchGame {
    id: number,
    matchStatus: MatchStatus,
    opponentOneResult: ParticipantMatchGameResult | null,
    opponentTwoResult: ParticipantMatchGameResult | null,
    stage: {id: number},
    match: {id: number},
    number: number
}

export default MyMatchGame