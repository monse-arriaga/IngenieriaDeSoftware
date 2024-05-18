import RoundRobinMode from "./RoundRobinMode";

interface StageSettings {
    id: number,
    size: number,
    balanceByes: boolean,
    matchesChildCount: number,
    groupCount: number,
    roundRobinMode: RoundRobinMode,
    stage: {id: number}
}

export default StageSettings