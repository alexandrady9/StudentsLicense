import * as React from "react";
import * as ReactDOM from "react-dom";
import App from "./app/App";

import {Provider} from 'react-redux'
import store from "./app/store";
import './index.scss'
import loadLang from "./app/variables";
import {loadLiterals} from "./app/reducers/literalsReducer";
import {createGenerateClassName, StylesProvider} from "@material-ui/core/styles";
import HandleApiErrors from "./app/api/handleApi/HandleApiErrors";
import HandleApiSuccess from "./app/api/handleApi/HandleApiSuccess";


const generateClassName = createGenerateClassName({
  productionPrefix: "",
  disableGlobal: true,
  seed: "",
});

const lang = loadLang();
let storeObj: any = store();
storeObj.dispatch(loadLiterals(lang));
const rootElement = document.getElementById('root');
ReactDOM.render(
  <Provider store={storeObj}>
    <HandleApiErrors/>
    <HandleApiSuccess/>
    <StylesProvider generateClassName={generateClassName}>
      <App/>
    </StylesProvider>
  </Provider>,
  rootElement
);
