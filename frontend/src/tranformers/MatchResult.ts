import { ParticipantResult } from "brackets-model"
import ParticipantMatchResullt from "../types/ParticipantMatchResult"
import Result from "./Result";

class MatchResultT {
    to (value:  ParticipantMatchResullt | null): ParticipantResult | null {
        if (value == null) return null; 
        return {
            id: value.participant == null ? null: value.participant.id,
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: Result.to(value.result)
        }
    }

    from (value: ParticipantResult | null): ParticipantMatchResullt | null{
        if (value == null) return null; 
        return {
            participant: value.id == null ? null : {
                id: value.id as unknown as number
            },
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: Result.from(value.result),
        }
    }
}

export default MatchResultT