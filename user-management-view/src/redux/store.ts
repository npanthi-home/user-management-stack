import { configureStore } from '@reduxjs/toolkit';
import rbacReducer from './rbac/rbacReducer';

const store = configureStore({
  reducer: {
    rbac: rbacReducer,
  },
});

export default store;