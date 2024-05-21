import MatchResult from "./MatchResult";

interface ParticipantMatchGameResult {
    id?: number,
    participant: {id: number},
    position?: number,
    forfeit?: boolean,
    score?: number,
    result?: MatchResult
}

export default ParticipantMatchGameResult