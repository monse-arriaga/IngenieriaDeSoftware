import { ParticipantResult } from "brackets-model"
import MatchResult from "../types/MatchResult"
import ParticipantMatchGameResult from "../types/ParticipantMatchGameResult";

class MatchGameResultT {
    to (value:  ParticipantMatchGameResult | null): ParticipantResult | null {
        if (value == null) return null; 
        return {
            id: value.participant.id,
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: value.result
        }
    }

    from (value: ParticipantResult | null): ParticipantMatchGameResult | null{
        if (value == null) return null; 
        return {
            participant: {
                id: value.id as number
            },
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: value.result as MatchResult,
        }
    }
}

export default MatchGameResultT