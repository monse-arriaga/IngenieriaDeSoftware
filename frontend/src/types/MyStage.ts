import MyStageSettings from "./MyStageSettings";
import MyStageType from "./MyStageType";

interface MyStage {
    id: number,
    tournamentId: string,
    name: string,
    type: MyStageType | String,
    stageSettings: MyStageSettings,
    number: number,
}

export default MyStage