import { createAsyncThunk } from '@reduxjs/toolkit';
import RbacState from '../state/RbacState';

const fetchPermissions = createAsyncThunk<RbacState>('rbac/fetchPermissions', async () => {
    try {
        const response = await fetch('http://localhost:8080/rbac', {
            method: 'GET',
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                "Access-Control-Allow-Origin": "*",
                Accept: "application/json",
            },
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching RBAC data:', error);
        throw error;
    }
});

export default fetchPermissions;