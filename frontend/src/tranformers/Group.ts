import { Group } from "brackets-model";
import MyGroup from "../types/MyGroup";

class GroupT {
    to (value:  MyGroup): Group {
        return {
            id: value.id,
            stage_id: value.stage.id,
            number: value.number
        }
    }

    from (value: Group): MyGroup {
        return {
            id: value.id as number,
            stage: {
                id: value.stage_id as number
            },
            number: value.number
        }
    }
}

export default GroupT