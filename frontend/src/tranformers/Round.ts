import { Round } from "brackets-model"
import MyRound from "../types/Round"

class RoundT {
    to (value:  MyRound): Round {
        return {
            id: value.id,
            stage_id: value.stage.id,
            group_id: value.group.id,
            number: value.number
        }
    }

    from (value: Round): MyRound {
        return {
            id: value.id as number,
            stage: {
                id: value.id as number
            },
            group: {
                id: value.id as number
            },
            number: value.number
        }
    }
}

export default RoundT