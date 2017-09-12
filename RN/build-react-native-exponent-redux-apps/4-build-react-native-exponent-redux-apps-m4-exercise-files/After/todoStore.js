import { createStore } from 'redux';


const defaultTodos = [
    {
        task: 'Initial todo in store',
        state: 'pending',
    },
];

const defaultState = {
    todos: defaultTodos,
    filter: 'pending',
    filteredTodos: defaultTodos,
};

function getFilteredTodos(allTodos, filter) {
    return allTodos.filter(todo => todo.state === filter);
}

function todos(state = defaultState, action) {
    switch (action.type) {
    case 'ADD_TODO':
        const allTodos = state.todos.concat([{
            task: action.task,
            state: 'pending',
        }]);

        return Object.assign({}, state, {
            todos: allTodos,
            filteredTodos: getFilteredTodos(allTodos, state.filter),
        });

    case 'DONE_TODO':
        const doneTodo = Object.assign({}, action.todo, {
            state: 'done',
        });

        const allTodosContainingDone = state.todos
            .map((todo) => {
                return todo === action.todo ? doneTodo : todo;
            });

        return Object.assign({}, state, {
            todos: allTodosContainingDone,
            filteredTodos: getFilteredTodos(allTodosContainingDone, state.filter),
        });
    case 'TOGGLE_STATE':
        const filter = state.filter === 'pending' ? 'done' : 'pending';
        return Object.assign({}, state, {
            filter,
            filteredTodos: getFilteredTodos(state.todos, filter),
        });
    default:
        return state;
    }
}

export default createStore(todos);
