/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React from 'react';
import {
  Button,
  View
} from 'react-native';

// import ToastExample from "./ToastModule/ToastExample";
import PaymentExample from "./ToastModule/PaymentExample";

class App extends React.Component {
  async showToast(){
    // ToastExample.show("Hello World");
    try{
      var token = await PaymentExample.doPayment();
      console.log(token);
    }catch(e){
      console.log(e);
    }
  }
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Button onPress={() => {
          this.showToast()
        }} title="Clike Me" />
      </View>
    );
  }
}

export default App;
