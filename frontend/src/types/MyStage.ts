import MyStageSettings from "./MyStageSettings";
import MyStageType from "./MyStageType";

interface MyStage {
    id: number,
    tournamentId: string,
    name: string,
    type: MyStageType
    stageSettings: MyStageSettings,
    number: number,
}

export default MyStage