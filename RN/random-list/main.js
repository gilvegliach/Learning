import Expo from 'expo';
import React from 'react';
import { StyleSheet, Text, View, ListView } from 'react-native';

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});

class ListList extends React.Component {
  constructor(props, context) {
      super(props, context);
      const ds = new ListView.DataSource({
          rowHasChanged: (r1, r2) => r1 !== r2,
      });

      this.state = {
          dataSource: ds.cloneWithRows(props.lists),
      };
  }

  renderRow(list) {
      return (
      <Text>{list.title}</Text>
    );
  }

  render() {
      return (
      <ListView
          dataSource={this.state.dataSource}
          renderRow={this.renderRow.bind(this)}
      />
    );
  }
}

ListList.propTypes = {
    lists: React.PropTypes.arrayOf(React.PropTypes.object).isRequired,
};

class App extends React.Component {
  constructor(props, context) {
      super(props, context);

      this.state = {
          lists: [
              {
                  title: 'Lunch',
              },
              {
                  title: 'Sports',
              },
          ],
      };
  }
  render() {
      return (
        <View style={styles.container}>
          <Text>Open up main.js to start working on your app!</Text>
          <ListList
              lists = {this.state.lists}
          />
        </View>
    );
  }
}

Expo.registerRootComponent(App);
