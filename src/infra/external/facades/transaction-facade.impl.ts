import { AuthorizeTransactionFacade } from "@/data/protocols";
import { retrySyncCalls } from "../helpers";
import { ServerErrorException } from "@/presentation/exceptions";

export class TransactionFacadeImpl implements AuthorizeTransactionFacade {
    private readonly url: string = "https://util.devi.tools/api/v2/authorize";
    private readonly retries: number = 5;

    async authorize(): Promise<boolean> {
        try {
            const isAuthorized = await retrySyncCalls(this.url, this.retries);

            console.log(isAuthorized);
            return isAuthorized.data.authorization;
        } catch (error) {
            throw new ServerErrorException();
        }
    }
}
