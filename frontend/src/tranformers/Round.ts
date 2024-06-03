import { Round } from "brackets-model"
import MyRound from "../types/Round"

class RoundT {
    to (value:  MyRound): Round {
        return {
            id: value.id != undefined ? value.id : 0,
            stage_id: value.stage.id,
            group_id: value.group.id,
            number: value.number
        }
    }

    from (value: Round): MyRound {
        return {
            stage: {
                id: value.stage_id as number
            },
            group: {
                id: value.group_id as number
            },
            number: value.number
        }
    }
}

export default RoundT