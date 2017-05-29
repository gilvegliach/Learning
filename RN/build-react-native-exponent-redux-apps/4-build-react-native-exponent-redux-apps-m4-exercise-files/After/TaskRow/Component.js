import React from 'react-native';
import Render from './Render';

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
        return Render.bind(this)(styles);
    }
}

TaskRow.propTypes = {
    onDone: React.PropTypes.func.isRequired,
    todo: React.PropTypes.shape({
        task: React.PropTypes.string.isRequired,
    }).isRequired,
};

export default TaskRow;
