export type User = {
    id: number;
    cpf: string;
    nome: string;
    email: string;
    userType: UserTypeEnum;
};

export enum UserTypeEnum {
    LOJISTA,
    COMUM,
}
