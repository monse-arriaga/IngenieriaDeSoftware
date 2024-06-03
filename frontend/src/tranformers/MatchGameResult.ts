import { ParticipantResult } from "brackets-model"
import ParticipantMatchGameResult from "../types/ParticipantMatchGameResult";
import Result from "./Result";

class MatchGameResultT {
    to (value:  ParticipantMatchGameResult | null): ParticipantResult | null {
        if (value == null) return null; 
        return {
            id: value.participant.id,
            position: value.position,
            forfeit: value.forfeit,
            score: value.score,
            result: Result.to(value.result)
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
            result: Result.from(value.result),
        }
    }
}

export default MatchGameResultT