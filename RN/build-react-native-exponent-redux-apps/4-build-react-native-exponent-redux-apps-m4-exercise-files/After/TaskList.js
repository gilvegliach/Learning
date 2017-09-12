import React from 'react-native';

const {
    View,
    ListView,
    TouchableHighlight,
    Text,
    Switch,
} = React;

import TaskRow from './TaskRow/Component';

const styles = React.StyleSheet.create({
    container: {
        paddingTop: 40,
        backgroundColor: '#F7F7F7',
        flex: 1,
        justifyContent: 'flex-start',
    },
    button: {
        height: 60,
        borderColor: '#05A5D1',
        borderWidth: 2,
        backgroundColor: '#333',
        margin: 20,
        justifyContent: 'center',
        alignItems: 'center',
    },
    buttonText: {
        color: '#FAFAFA',
        fontSize: 20,
        fontWeight: '600',
    },
});

class TaskList extends React.Component {
    constructor(props, context) {
        super(props, context);

        const ds = new ListView.DataSource({
            rowHasChanged: (r1, r2) => r1 !== r2,
        });

        this.state = {
            dataSource: ds.cloneWithRows(props.filteredTodos),
        };
    }

    componentWillReceiveProps(nextProps) {
        const dataSource = this
            .state
            .dataSource
            .cloneWithRows(nextProps.filteredTodos);

        this.setState({ dataSource });
    }

    renderRow(todo) {
        return (
            <TaskRow
                onDone={this.props.onDone}
                todo={todo}
            />
        );
    }

    render() {
        return (
            <View style={styles.container}>
            <View
                style={{
                    flexDirection: 'row',
                    padding: 10,
                }}
            >
                <Switch
                    onValueChange={this.props.onToggle}
                    style={{
                        marginBottom: 10,
                    }}
                    value={this.props.filter !== 'pending'}
                />
                <Text style={{
                    fontSize: 20,
                    paddingLeft: 10,
                    paddingTop: 3,
                }}
                >
                Showing {this.props.filteredTodos.length} {this.props.filter} todo(s)
                </Text>
            </View>

                <ListView
                    dataSource={this.state.dataSource}
                    renderRow={this.renderRow.bind(this)}
                />

                <TouchableHighlight
                    onPress={this.props.onAddStarted}
                    style={styles.button}
                >
                    <Text
                        style={styles.buttonText}
                    >
                        Add one
                    </Text>
                </TouchableHighlight>
            </View>
        );
    }
}

TaskList.propTypes = {
    filter: React.PropTypes.string.isRequired,
    filteredTodos: React.PropTypes
        .arrayOf(React.PropTypes.object).isRequired,
    onAddStarted: React.PropTypes.func.isRequired,
    onDone: React.PropTypes.func.isRequired,
    onToggle: React.PropTypes.func.isRequired,
};

export default TaskList;
