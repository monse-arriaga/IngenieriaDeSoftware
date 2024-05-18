import { CrudInterface, Table } from "brackets-manager";

export class tournamentStorage implements CrudInterface {

    public insert<T>(table: Table, value: T): Promise<number>;
    
    public insert<T>(table: T, values: T[]): Promise<boolean>;
    
    public async insert<T>(table: Table, values: T | T[]): Promise<number | boolean> {
        if(table == null) return -1;
        return true;    
    }

    public select<T>(table: Table): Promise<T[] | null>;

    public select<T>(table: Table, id: number): Promise<T | null>;

    public select<T>(table: Table, filter: Partial<T>): Promise<T[] | null>;

    public async select<T>(table: Table, filter?: number | Partial<T>): Promise< T |  T[] | null> {
        return null;    
    }

    public update<T>(table: Table, id: number, value: T): Promise<boolean>;
 
    public update<T>(table: Table, filter: Partial<T>, value: Partial<T>): Promise<boolean>;
 
    public async update(table: unknown, filter: unknown, value: unknown): Promise<boolean> {
        return false;        
    }

    public delete<T>(table: Table): Promise<boolean>;
    
    public delete<T>(table: Table, filter: Partial<T>): Promise<boolean>;
    
    public async delete<T>(table: Table, filter?: T | T[]): Promise<boolean> {
        return false;
    }

}