import MatchStatus from "./MatchStatus";
import ParticipantMatchResullt from "./ParticipantMatchResult";

interface MyMatch {
    id: number,
    matchStatus: MatchStatus,
    opponentOneResult:ParticipantMatchResullt | null,
    opponentTwoResult: ParticipantMatchResullt | null,
    stage: {id: number},
    group: {id: number},
    round: {id: number},
    number: number,
    childCount: number
}

export default MyMatch