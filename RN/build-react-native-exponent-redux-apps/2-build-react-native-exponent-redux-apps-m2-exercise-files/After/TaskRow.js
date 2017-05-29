import React from 'react-native';
const {
    Text,
    View,
    TouchableHighlight,
} = React;

const styles = React.StyleSheet.create({
    container: {
        backgroundColor: '#fff',
        borderWidth: 1,
        borderColor: '#E7E7E7',
        padding: 20,
        flex: 1,
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginBottom: 20,
        marginLeft: 20,
        marginRight: 20,
    },
    label: {
        fontSize: 20,
        fontWeight: '300',
    },
    doneButton: {
        borderRadius: 5,
        backgroundColor: '#EAEAEA',
        padding: 5,
    },
});

class TaskRow extends React.Component {
    onDonePressed() {
        this.props.onDone(this.props.todo);
    }

    render() {
        return (
            <View style={styles.container}>
                <Text
                    style={styles.label}
                >{this.props.todo.task}</Text>

                <TouchableHighlight
                    onPress={this.onDonePressed.bind(this)}
                    style={styles.doneButton}
                >
                    <Text>Done</Text>
                </TouchableHighlight>
            </View>
        );
    }
}

TaskRow.propTypes = {
    onDone: React.PropTypes.func.isRequired,
    todo: React.PropTypes.shape({
        task: React.PropTypes.string.isRequired,
    }).isRequired,
};

export default TaskRow;
