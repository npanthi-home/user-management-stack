import { createAsyncThunk } from "@reduxjs/toolkit";
import RbacState from "../state/RbacState";

const savePermissions = createAsyncThunk<RbacState, RbacState>(
    'rbac/savePermissions',
    async (state: RbacState) => {
      try {
        const response = await fetch('http://localhost:8080/rbac', {
          method: 'PATCH',
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            'Access-Control-Allow-Origin': '*',
            Accept: 'application/json',
          },
          body: JSON.stringify(state),
        });
  
        const data: RbacState = await response.json();
        return data;
      } catch (error) {
        console.error('Error fetching RBAC data:', error);
        throw error;
      }
    }
  );
  
  export default savePermissions;