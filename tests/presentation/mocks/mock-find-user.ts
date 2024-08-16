import { UserTypeEnum } from "@/domain/models";
import { FindUserById } from "@/domain/use-cases";

export class FindUserByIdSpy implements FindUserById {
    id?: number;
    user?: FindUserById.Result;

    async findUserById(id: number): Promise<FindUserById.Result> {
        const user = {
            cpf: "valid_cpf",
            email: "valid_email",
            id,
            nome: "valid_nome",
            userType: UserTypeEnum.LOJISTA,
        };

        this.id = user.id;
        this.user = user;
        return user;
    }
}
