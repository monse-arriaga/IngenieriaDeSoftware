import { ParticipantResult } from "brackets-model"
import ParticipantMatchResullt from "../types/ParticipantMatchResult"
import ResultT from "./Result";

class MatchResultT {
    tranformer = new ResultT()

    to (value:  ParticipantMatchResullt | null): ParticipantResult | null {
        if (value == null) return null; 
        return {
            id: value.participant == null ? null: value.participant.id,
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: this.tranformer.to(value.result)
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
            result: this.tranformer.from(value.result),
        }
    }
}

export default MatchResultT