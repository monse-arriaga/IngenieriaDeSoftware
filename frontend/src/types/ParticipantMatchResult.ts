import MatchResult from "./MatchResult";

interface ParticipantMatchResullt {
    id?: number,
    participant: {id: number} | null,
    position?: number,
    forfeit?: boolean,
    score?: number,
    result?: MatchResult
}

export default ParticipantMatchResullt