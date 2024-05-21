import { CrudInterface, Table } from "brackets-manager";
import StageService from "../services/StageService";
import { Stage } from "brackets-model";

export class tournamentStorage implements CrudInterface {


    public insert<T>(table: Table, value: T): Promise<number>;
    public insert<T>(table: T, values: T[]): Promise<boolean>;
    
    public async insert<T>(table: Table, values: T | T[]): Promise<number | boolean> {
        const isArray = Array.isArray(values)
        try {
            switch (table) {
                case "stage":
                    StageService.create(values as Stage)
                    break;
                case "group":
                    console.log("Processing group table...");
                    break;
                case "round":
                    console.log("Processing round table...");
                    break;
                case "match":
                    console.log("Processing match table...");
                    break;
                case "match_game":
                    console.log("Processing match game table...");
                    break;
                case "participant":
                    console.log("Processing participant table...");
                    break;
                default:
                    console.log("Unknown table");
            }
        } catch (error) {
            return isArray ? false : -1;
        }
        return isArray ? true : 1;
    }

    public select<T>(table: Table): Promise<T[] | null>;
    public select<T>(table: Table, id: number): Promise<T | null>;

    public select<T>(table: Table, filter: Partial<T>): Promise<T[] | null>;

    public async select<T>(table: Table, filter?: number | Partial<T>): Promise< T |  T[] | null> {
        if(table) return null;
        if(filter) return null;
        return null;  
    }

    public update<T>(table: Table, id: number, value: T): Promise<boolean>;
    public update<T>(table: Table, filter: Partial<T>, value: Partial<T>): Promise<boolean>;
 
    public async update(table: unknown, filter: unknown, value: unknown): Promise<boolean> {
        if(table) return true;
        if(filter) return false;
        if(value) return true;
        return false;        
    }

    public delete<T>(table: Table): Promise<boolean>;
    public delete<T>(table: Table, filter: Partial<T>): Promise<boolean>;
    
    public async delete<T>(table: Table, filter?: T | T[]): Promise<boolean> {
        if(table) return true;
        if(filter) return false;
        return false;  
    }

}