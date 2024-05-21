import { ParticipantResult } from "brackets-model"
import ParticipantMatchResullt from "../types/ParticipantMatchResult"
import MatchResult from "../types/MatchResult"

class MatchGameT {
    to (value:  ParticipantMatchResullt | null): ParticipantResult | null {
        if (value == null) return null; 
        return {
            id: value.participant.id,
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: value.result
        }
    }

    from (value: ParticipantResult | null): ParticipantMatchResullt | null{
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

export default MatchGameT