import { PayerMiddleware } from "@/presentation/middlewares";
import { Middleware } from "@/presentation/protocols";
import { makeFindUserByIdAndRole } from "../use-cases";

export const makePayerMiddleware = (): Middleware => {
    return new PayerMiddleware(makeFindUserByIdAndRole());
};
