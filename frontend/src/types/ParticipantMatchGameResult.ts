import MatchResult from "./MatchResult";

interface ParticipantMatchGameResult {
    id: number,
    participant: {id: number},
    position: number,
    forfeit: boolean,
    score: number,
    result: MatchResult,
    opponentOneMatchGame: {id: number},
    opponentTwoMatchGame: {id: number},   
}

export default ParticipantMatchGameResult