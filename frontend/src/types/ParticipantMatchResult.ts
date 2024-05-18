import MatchResult from "./MatchResult";

interface ParticipantMatchResullt {
    id: number,
    participant: {id: number},
    position: number,
    forfeit: boolean,
    score: number,
    result: MatchResult,
    opponentOneMatch: {id: number},
    opponentTwoMatch: {id: number}
}

export default ParticipantMatchResullt