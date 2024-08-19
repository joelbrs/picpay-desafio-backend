import { SendEmailFacade } from "@/data/protocols";
import { retrySyncCalls } from "../helpers";
import { ServerErrorException } from "@/presentation/exceptions";

export class EmailFacadeImpl implements SendEmailFacade {
    private readonly url: string = "https://util.devi.tools/api/v1/notify";
    private readonly retries: number = 5;

    async sendEmail() {
        try {
            await retrySyncCalls(this.url, this.retries, { method: "POST" });
        } catch (error) {
            throw new ServerErrorException();
        }
    }
}
