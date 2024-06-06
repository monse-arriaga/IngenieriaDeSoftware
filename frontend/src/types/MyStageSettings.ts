import MyGrandFinalType from "./MyGrandFinalType";
import MyRoundRobinMode from "./MyRoundRobinMode";

interface MyStageSettings {
    id?: number,
    size?: number,
    balanceByes?: boolean,
    matchesChildCount?: number,
    groupCount?: number,
    roundRobinMode?: MyRoundRobinMode,
    consolationFinal?: boolean,
    skipFirstRound?: boolean,
    grandFinal?: MyGrandFinalType,
    seedOrdering?: string[]
}

export default MyStageSettings