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
  showToast = () => {
    ToastExample.show("Hello World");
  }
  render() {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <Button onPress={() => {
          PaymentExample.doPayment()
        }} title="Clike Me" />
      </View>
    );
  }
}

export default App;
