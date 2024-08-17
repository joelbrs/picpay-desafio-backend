import { adaptMiddleware } from "../adapters";
import { makePayerMiddleware } from "../factories";

export const payerMiddleware = adaptMiddleware(makePayerMiddleware());
