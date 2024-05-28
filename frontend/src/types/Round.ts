import MyStageSettings from "./MyStageSettings"
import MyStageType from "./MyStageType"

interface MyRound {
    id?: number,
    stage: {
        id: number,
        tournamentId?: string,
        name?: string,
        type?: MyStageType,
        stageSettings?: MyStageSettings,
        number?: number
    },
    group: {
        id: number,
        stage?: {
            id: number,
            tournamentId?: string,
            name?: string,
            type?: MyStageType,
            stageSettings?: MyStageSettings,
            number?: number
        },
        number?: number,
    },
    number: number
}

export default MyRound