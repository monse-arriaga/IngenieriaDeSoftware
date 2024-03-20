import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    host: true,
    port: 8000,
    watch: {
      usePolling: true
    }
  },
  plugins: [vue()],
})
